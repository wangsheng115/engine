// Copyright 2013 The Flutter Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package io.flutter.embedding.engine.plugins.service;

import android.app.Service;
import android.arch.lifecycle.Lifecycle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Control surface through which a {@link Service} attaches to a {@link FlutterEngine}.
 *
 * <p>A {@link Service} that contains a {@link FlutterEngine} should coordinate itself with the
 * {@link FlutterEngine}'s {@code ServiceControlSurface}.
 */
public interface ServiceControlSurface {
  /**
   * Call this method from the {@link Service} that is running the {@link FlutterEngine} that is
   * associated with this {@code ServiceControlSurface}.
   *
   * <p>Once a {@link Service} is created, and its associated {@link FlutterEngine} is executing
   * Dart code, the {@link Service} should invoke this method. At that point the {@link
   * FlutterEngine} is considered "attached" to the {@link Service} and all {@link ServiceAware}
   * plugins are given access to the {@link Service}.
   *
   * <p>{@code isForeground} should be true if the given {@link Service} is running in the
   * foreground, false otherwise.
   */
  void attachToService(
      @NonNull Service service, @Nullable Lifecycle lifecycle, boolean isForeground);

  /**
   * Call this method from the {@link Service} that is attached to this {@code
   * ServiceControlSurfaces}'s {@link FlutterEngine} when the {@link Service} is about to be
   * destroyed.
   *
   * <p>This method gives each {@link ServiceAware} plugin an opportunity to clean up its references
   * before the {@link Service is destroyed}.
   */
  void detachFromService();

  /**
   * Call this method from the {@link Service} that is attached to this {@code
   * ServiceControlSurface}'s {@link FlutterEngine} when the {@link Service} goes from background to
   * foreground.
   */
  void onMoveToForeground();

  /**
   * Call this method from the {@link Service} that is attached to this {@code
   * ServiceControlSurface}'s {@link FlutterEngine} when the {@link Service} goes from foreground to
   * background.
   */
  void onMoveToBackground();
}
